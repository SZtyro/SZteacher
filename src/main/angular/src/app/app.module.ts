import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {WordComponent} from './component/word/word.component';
import {FormsModule} from "@angular/forms";
import {MatFormFieldModule} from '@angular/material/form-field';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from '@angular/material/icon';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { WordListComponent } from './component/word-list/word-list.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { WordListContentComponent } from './component/word-list/word-list-content/word-list-content.component';
import {MatTableModule} from "@angular/material/table";
import {MatRippleModule} from "@angular/material/core";
import { ListComponent } from './component/list/list.component';
import { ExamComponent } from './component/exam/exam.component';

export function HttpLoaderFactory(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient);
}

@NgModule({
  declarations: [
    AppComponent,
    WordComponent,
    WordListComponent,
    WordListContentComponent,
    ListComponent,
    ExamComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        MatFormFieldModule,
        BrowserAnimationsModule,
        MatButtonModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        }),
        MatSelectModule,
        MatInputModule,
        MatIconModule,
        MatCheckboxModule,
        MatTableModule,
        MatRippleModule

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
