package com.Hospitoolity.Jobs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.Hospitoolity.Jobs.service.HangmanService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/hangman")
public class HangmanController {

    private final HangmanService hangmanService;

    public HangmanController(HangmanService hangmanService) {
        this.hangmanService = hangmanService;
    }

    @PostMapping("/guess/{letter}")
    public ResponseEntity<String> guessLetter(@PathVariable char letter) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();

        String word = (String) session.getAttribute("word");
        String guessedLetters = (String) session.getAttribute("guessedLetters");
        Integer failedGuessCount = (Integer) session.getAttribute("failedGuessCount");

        if (guessedLetters == null) {
            guessedLetters = "";
        }

        if (failedGuessCount == null) {
            failedGuessCount = 0;
        }

        if (hangmanService.checkGuess(word, letter)) {
            guessedLetters += letter;
            session.setAttribute("guessedLetters", guessedLetters);
        } else {
            failedGuessCount++;
            session.setAttribute("failedGuessCount", failedGuessCount);
        }

        String maskedWord = hangmanService.revealGuessedLetters(word, guessedLetters);

        if (hangmanService.isGameLost(failedGuessCount)) {
            return ResponseEntity.ok("Game over, you lost! The word was: " + word);
        }

        if (hangmanService.isGameWon(word, guessedLetters)) {
            return ResponseEntity.ok("Congratulations, you won! The word was: " + word);
        }

        return ResponseEntity.ok(maskedWord);
    }
    @PostMapping("/start")
    public ResponseEntity<String> startGame() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();

        // Get a new random word and save it in the session
        String word = hangmanService.pickRandomWord();
        session.setAttribute("word", word);

        // Reset guessed letters and failed guess count
        session.setAttribute("guessedLetters", "");
        session.setAttribute("failedGuessCount", 0);

        // Return the initial game state (the word with all letters masked)
        return ResponseEntity.ok(hangmanService.revealGuessedLetters(word, ""));
    }
}