import {Injectable} from '@angular/core';

@Injectable()
export class MeasurementProvider {
    private isMeasuring: boolean =  false;
    private measuringStarted: any;
    private clickCount: number;
    constructor() { }

    public Measure() : string{
        if(this.isMeasuring){
            this.isMeasuring = false;
            var timeResult = performance.now() - this.measuringStarted;
            var result = timeResult / 1000 * Math.floor(Math.random() * 500) + 40
            if(this.clickCount == 0)
              result = 0;
            var str = `Kliknięcia: ${this.clickCount}, Odległość: ${result}`;
            return str
          }
          else{
            this.isMeasuring = true;
            this.clickCount = 0;
            this.measuringStarted = performance.now();
          }
          return "";
    }

    public Clicked(){
        this.clickCount++;
        return true;
    }

    public MeasureCancel(){
      this.isMeasuring = false;
      return true;
    }
}