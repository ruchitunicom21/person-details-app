export class Person {
	public constructor(init?: Partial<Person>) {
        Object.assign(this, init);
    }
}
