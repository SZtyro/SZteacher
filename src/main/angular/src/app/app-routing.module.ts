import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {WordComponent} from "./component/word/word.component";
import {LanguageResolverService} from "./service/resolver/language/language-resolver.service";
import {WordListComponent} from "./component/word-list/word-list.component";
import {ListResolverService} from "./service/resolver/list/list-resolver.service";
import {WordListContentComponent} from "./component/word-list/word-list-content/word-list-content.component";

const routes: Routes = [
  {
    path: 'word', component: WordComponent, resolve: {
      languages: LanguageResolverService
    }
  },
  {
    path: 'list', children: [
      {
        path: ':id', component: WordListComponent, resolve: {
          languages: LanguageResolverService,
          list: ListResolverService
        }
      },
      {
        path: ':id/words', component: WordListContentComponent, resolve: {

        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
