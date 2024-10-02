import {ApplicationForm} from "./ApplicationForm";

export interface ExamEvent {
  id: number;
  examEventName: string;
  maintenanceDate: string;
  applicationDateRange: {
    startDate: string;
    endDate: string;
  };
  price: number;
  numberOfApplicants: number;
  typeName: string;
  applicationForms: ApplicationForm[];
}
