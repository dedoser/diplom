import {Component, OnInit} from '@angular/core';
import {SolutionService} from "../../services/solution/solution.service";
import {Solution} from "../../common/solution";
import {ActivatedRoute} from "@angular/router";
import {QualityService} from "../../services/quality/quality.service";
import {Quality} from "../../common/quality";
import {TaskService} from "../../services/task/task.service";
import {Task} from "../../common/task";

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
  task: Task;
  params: Map<string, number[]> = new Map<string, number[]>();

  constructor(private solutionService: SolutionService,
              private route: ActivatedRoute,
              private qualityService: QualityService,
              private taskService: TaskService) { }

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
    );
    this.qualityService.getQuality(this.solutionId).subscribe(
      data =>
        this.quality = data
    );
    this.taskService.getTask(this.taskId).subscribe(
      data =>
        this.task = data
    );
  }

  isValidRiseTime() : boolean {
    return this.isValidField('riseTime', this.task.riseTime, +this.quality.riseTime);
  }

  isValidSettlingTime() : boolean {
    return this.isValidField('settlingTime', this.task.settlingTime, +this.quality.settlingTime);
  }

  isValidSettlingMax() : boolean {
    return this.isValidField('settlingMax', this.task.settlingMax, +this.quality.settlingMax);
  }

  isValidSettlingMin() : boolean {
    return this.isValidField('settingMin', this.task.settlingMin, +this.quality.settlingMin);
  }

  isValidOvershoot() : boolean {
    return this.isValidField('overshoot', this.task.overshoot, +this.quality.overshoot);
  }

  isValidUndershoot() : boolean {
    return this.isValidField('undershoot', this.task.undershoot, +this.quality.undershoot);
  }

  isValidPeak() : boolean {
    return this.isValidField('peak', this.task.peak, +this.quality.peak);
  }

  isValidPeakTime() : boolean {
    return this.isValidField('peakTime', this.task.peakTime, +this.quality.peakTime);
  }


  private parseParam(key: string, param: string): number[] {
    let trimParam = param.substring(1, param.length - 1);
    let minMax = trimParam.split(';');
    return [+minMax[0], +minMax[1]];
  }

  private isValidField(key: string, param: string, solutionValue: number) : boolean {
    let minMax = this.params.get(key);
    if (minMax != undefined) {
      return solutionValue >= minMax[0] && solutionValue <= minMax[1];
    }
    if (param.length === 0) {
      return true;
    }
    minMax = this.parseParam(key, param);
    console.log(minMax)
    console.log(solutionValue)
    console.log(this.task)
    this.params.set(key, minMax);
    return solutionValue >= minMax[0] && solutionValue <= minMax[1];
  }
}
