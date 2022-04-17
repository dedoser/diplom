import { Component, OnInit } from '@angular/core';
import {SolutionService} from "../../services/solution/solution.service";
import {Solution} from "../../common/solution";
import {ActivatedRoute} from "@angular/router";
import {QualityService} from "../../services/quality/quality.service";
import {Quality} from "../../common/quality";

@Component({
  selector: 'app-solutions',
  templateUrl: './solutions.component.html',
  styleUrls: ['./solutions.component.css']
})
export class SolutionsComponent implements OnInit {

  solution: Solution;
  quality: Quality;
  solutionId: number;
  taskId: number;

  constructor(private solutionService: SolutionService,
              private route: ActivatedRoute,
              private qualityService: QualityService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => {
        console.log("Get solution info");
        this.initIds();
        this.handleSolutionDetails()
      }
    )
  }

  initIds() {
    this.taskId = +this.route.snapshot.paramMap.get('taskId')!;
    this.solutionId = +this.route.snapshot.paramMap.get('solutionId')!;
  }

  handleSolutionDetails() {
    this.solutionService.getSolution(this.solutionId).subscribe(
      data => {
        this.solution = data;
        let start = this.solution.imageSystem.indexOf('assets');
        this.solution.imageSystem = this.solution.imageSystem.substring(start);
        this.solution.imagePlot = this.solution.imagePlot.substring(start);
      }
    )
    this.qualityService.getQuality(this.solutionId).subscribe(
      data =>
        this.quality = data
    )
  }
}
