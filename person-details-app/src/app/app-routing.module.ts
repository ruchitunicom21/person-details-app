import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonDetailsComponent } from './person-details/person-details.component';
import { PersonResolverService } from './person-details/person-resolver.service';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [ 
  { path: 'persondetails', component:  PersonDetailsComponent , resolve: {
              person: PersonResolverService
            }},
  { path: '',
    redirectTo: '/persondetails',
    pathMatch: 'full'
  },
  { path: '**', component: PageNotFoundComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
