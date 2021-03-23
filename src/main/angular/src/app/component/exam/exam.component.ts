import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.scss']
})
export class ExamComponent implements OnInit {


  failedWords: any[] = [];


  selectedWord;
  question;
  questionTimer;

  list;
  words: any[];

  answer;

  constructor(
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.route.data.subscribe(data => {
      console.log(data)
      this.list = data.list;
      this.words = data.words;
      this.ask();

    })
  }


  ask() {
    console.log("ask")
    if (this.words.length > 0) {
      this.answer = "";
      let randomIndex = Math.floor(Math.random() * this.words.length);
      console.log(randomIndex);
      console.log(this.words)
      this.selectedWord = this.words[randomIndex];
      this.question = this.selectedWord.translation[Math.floor(Math.random() * this.words[randomIndex].translation.length)];
      //Deleting selected word
      this.words.splice(this.words.indexOf(this.selectedWord), 1);

      this.questionTimer = setTimeout(() => {
        if (this.isAnswerCorrect()) {
          this.ask()
        } else {
          this.failedWords.push(this.selectedWord);
          this.question = this.selectedWord.original;
          setTimeout(() => this.ask(), 2000)

        }
      }, 5000)
    } else {
      console.log("End of word list")
      if (this.failedWords.length > 0) {
        console.log("Loading failed words")
        this.words = this.failedWords;
        this.failedWords = [];
        this.ask();
      } else
        this.question = 'end';
    }


  }

  isAnswerCorrect() {
    return this.answer == this.selectedWord.original;
  }

  checkAnswer($event) {
    if (this.isAnswerCorrect()) {
      clearTimeout(this.questionTimer);
      this.ask()
    } else {
      clearTimeout(this.questionTimer);
      this.failedWords.push(this.selectedWord);
      this.question = this.selectedWord.original;
      setTimeout(() => this.ask(), 2000)

    }
  }
}
