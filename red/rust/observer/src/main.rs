
trait Observer<T> {
	fn update(&mut self, &T);
}

trait Observable<'a, T> {
	fn register(&mut self, &'a mut Observer<T>) -> &mut Self;
}

struct Monitor<'a> {
	observers: Vec<&'a mut Observer<i32>>,
	temp: i32,
}

impl <'a> Observable<'a, i32> for Monitor<'a> {
	fn register(&mut self, observer: &'a mut Observer<i32>) -> &mut Self {
		self.observers.push(observer);
		self
	}
}

impl <'a> Monitor<'a> {
	fn new<'b>() -> Monitor<'b> {
		Monitor { temp: 36, observers: vec![] }
	}

	fn set_temp(&mut self, temp: i32 ) {
		self.temp = temp;
		self.notify();
	}

	fn notify(&mut self) {
		for observer in self.observers.iter_mut() {
			observer.update(&self.temp);
		}
	}
}

struct PrintObserver;
struct TempChangeObserver {
	temps: Vec<i32>
}

impl Observer<i32> for PrintObserver {
	fn update(&mut self, x: &i32) {
		println!("Temperature is {}", x);
	}
}

impl Observer<i32> for TempChangeObserver {
	fn update(&mut self, x: &i32) {
		self.temps.push(*x);
	}
}

impl TempChangeObserver {
	fn new() -> TempChangeObserver {
		TempChangeObserver { temps: Vec::new() }
	}

	fn show_history(&self) {
		println!("History of temperature changes:");

		for tmp in self.temps.iter() {
			println!("  {}", tmp);
		}
	}
}

fn main() {
	let mut history = TempChangeObserver::new();
	let mut printer = PrintObserver;

	{
		let mut monitor = Monitor::new();
		monitor
			.register(&mut printer)
			.register(&mut history);

		monitor.set_temp(32);
		monitor.set_temp(36);
	}

	history.show_history();
}
