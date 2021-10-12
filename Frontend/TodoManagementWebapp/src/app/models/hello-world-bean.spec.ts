import { HelloWorldBean } from './hello-world-bean';

describe('HelloWorldBean', () => {
  it('should create an instance', () => {
    expect(new HelloWorldBean()).toBeTruthy();
  });
});
