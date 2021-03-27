import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { animate, keyframes, state, style, transition, trigger } from "@angular/animations";

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.scss'],
  animations: [
    trigger('examAnim', [
      state('timerStart', style({
        transform: 'translateY(0)',
        backgroundColor: '#893d63',
        color: 'white'
      })),
      state('timerStop', style({
        transform: 'translateY(100%)',
        backgroundColor: '#dfcae5',
        color: '#212529'
      })),
      state('wrongAnswer', style({
        transform: 'translateY(0)',
        backgroundColor: '#C62828',
        color: 'white'
      })),
      state('correctAnswer', style({
        transform: 'translateY(0)',
        backgroundColor: '#388E3C',
        color: 'white'
      })),

      transition('timerStart => timerStop', [
        animate('5000ms')
      ]),
      transition('* => timerStart', animate('200ms')),
      transition('* => wrongAnswer', animate('100ms')),
      transition('* => correctAnswer', animate('100ms')),
    ]),

    trigger('examAnimText', [
      state('black', style({
        color: 'black',
        outline: 'black',
        borderColor: 'black'
      })),
      state('white', style({
        color: 'white',
        outline: 'white',
        borderColor: 'white'
      })),


      transition('white => black', [
        animate('5000ms')
      ]),
      transition('black => white', animate('10ms'))
    ])

  ]
})

export class ExamComponent implements OnInit {

  /**Animation state */
  examAnimState = 'timerStart';
  /**Words to repeat */
  failedWords: any[] = [];
  /**Selected word to ask question */
  selectedWord;
  /**Question displayed to user */
  question;
  /**List object */
  list;
  /**Array of exam words */
  words: any[];
  /**User input */
  answer;
  /**Exam flag */
  isExam: boolean = true;

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
    if (this.words.length > 0) {
      this.examAnimState = 'timerStart';
      this.answer = "";
      let randomIndex = Math.floor(Math.random() * this.words.length);
      this.selectedWord = this.words[randomIndex];
      this.question = this.selectedWord.translation[Math.floor(Math.random() * this.words[randomIndex].translation.length)];
      //Deleting selected word
      this.words.splice(this.words.indexOf(this.selectedWord), 1);

    } else {
      console.log("End of word list")
      if (this.failedWords.length > 0) {
        console.log("Loading failed words")
        this.words = this.failedWords;
        this.failedWords = [];
        this.ask();
      } else
        this.isExam = false;
    }
  }

  checkQuestion() {

    if (this.answer.toLowerCase() === this.selectedWord.original) {
      setTimeout(() => { this.ask(); }, 500);
      this.examAnimState = 'correctAnswer';
    }
    else {
      this.question = this.selectedWord.original;
      this.failedWords.push(this.selectedWord);
      setTimeout(() => { this.ask(); }, 1500);
      this.examAnimState = 'wrongAnswer';
    }
  }

  onDone($event) {

    if ($event.toState === "timerStart") {
      this.examAnimState = "timerStop"
    }

    // if ($event.fromState === "void")
    //   this.examAnimState = "timerStop"

    if ($event.toState === "timerStop" && (this.examAnimState != 'correctAnswer' && this.examAnimState != 'wrongAnswer'))
      this.checkQuestion();

  }
}
