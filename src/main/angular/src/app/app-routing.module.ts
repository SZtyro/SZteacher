import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {WordComponent} from "./component/word/word.component";
import {LanguageResolverService} from "./service/resolver/language/language-resolver.service";

const routes: Routes = [
  {
    path: 'word', component: WordComponent, resolve: {
      languages: LanguageResolverService
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
