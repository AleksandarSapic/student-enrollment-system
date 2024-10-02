import {ApplicationForm} from "./ApplicationForm";

export interface User {
  id: number,
  firstName: string,
  middleName: string,
  lastName: string,
  email: string,
  price: number,
  applicationForms: ApplicationForm[],
}
