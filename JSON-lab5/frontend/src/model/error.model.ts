export interface IError {
  status: number;
  message: string;
}

export class Error implements IError {
  constructor(public status: number, public message: string) {}
}
