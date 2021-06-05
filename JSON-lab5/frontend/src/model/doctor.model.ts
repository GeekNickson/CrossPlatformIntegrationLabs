import { Service } from './service.model';
import { Specialty } from './specialty.model';

export interface Doctor {
  id?: string;
  firstName: string;
  lastName: string;
  age: number;
  category: string;
  experience: number;
  vacationStart: Date;
  vacationEnd: Date;
  onVacation: boolean;
  specialty: Specialty;
  services: Service[];
}
