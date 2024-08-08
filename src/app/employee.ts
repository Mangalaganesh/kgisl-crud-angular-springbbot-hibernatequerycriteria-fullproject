export class Employee {
    emp_Id?: number;
    name: string='';
    emailId: string='';
    designation: string='';
    addresses: Address[] = [];
  }
  
  export class Address {
    id?: number;
    street:string='';
    city: string='';
    state: string='';
  }
  