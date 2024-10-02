import {ExamEvent} from "./ExamEvent";
import {User} from "./User";

export interface ApplicationForm {
  user: User,
  examEvent: ExamEvent,
  applicationNumber: string,
}
