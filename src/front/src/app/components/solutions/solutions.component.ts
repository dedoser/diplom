import { Component, OnInit } from '@angular/core';
import {SolutionService} from "../../services/solution/solution.service";
import {Solution} from "../../common/solution";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-solutions',
  templateUrl: './solutions.component.html',
  styleUrls: ['./solutions.component.css']
})
export class SolutionsComponent implements OnInit {

  solution: Solution;
  solutionId: number;
  taskId: number;

  constructor(private solutionService: SolutionService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => {
        console.log("Get solution info");
        this.initIds();
      }
    )
  }

  initIds() {
    this.taskId = +this.route.snapshot.paramMap.get('taskId')!;
    this.solutionId = +this.route.snapshot.paramMap.get('solutionId')!;
  }

  handleSolutionDetails() {
    this.solutionService.getSolution(this.solutionId).subscribe(
      data => this.solution = data
    )
  }
}
