package com;

import java.util.Optional;

import com.animal.model.Cat;

public class OptionalTutorial {

	public static void main(String... args) {
		Cat aCat = findCatByName("Fluffy");
		System.out.println(
				"\nWithout using an Optional<> the consumer may not be aware \nthat null return is possible, and neglect to check for null.");
		System.out.println("\taCat.toString(): " + aCat);
		try {
			System.out.println("\taCat.getName(): " + aCat.getName());
		} catch (Exception e) {
			System.err.println(
					"\tThere is no cat, and an exception occurred. \n\tThis System.err message is in the catch block.");
		}

		System.out.println(
				"\nUsing an Optional<> the consumer is made aware \nthat null return is possible, and is reminded to handle the null.");
		Optional<Cat> optionalCat1 = findOptionalCatByName("Fluffy");
		System.out.println("\noptionalCat1: " + optionalCat1);
		System.out.println(
				"\nOne way to handle the optional is to use the get() method, but this simply gives you a no such element exception. No bueno.");
		System.out.println("\nUsing the isPresent() method on the optional object to check is better.");
		if (optionalCat1.isPresent()) {
			System.out.println("\toptionalCat1.get().getAge(): " + optionalCat1.get().getAge());
		} else {
			System.out.println("\toptionalCat1.isPresent returned false, no cat.");
		}
		System.out.println(
				"\nUsing the orElse(T other) method of optional provides a tool to return the Cat in the optional object, or a different cat if empty.");
		Cat orElseCat = optionalCat1.orElse(new Cat("UNKNOWN", 0));
		System.out.println("\tCat orElseCat = optionalCat1.orElse(new Cat(\"UNKNOWN\", 0));");
		System.out.println("\t orElseCat.toString(): " + orElseCat);
		System.out.println("\t orElseCat.getAge(): " + orElseCat.getAge());
		
		System.out.println("\nThe map() Optional method can be used to process the object if present, and then other methods can be chained onto the statement.");
		System.out.println("\toptionalCat1.map(Cat::getAge).orElse(0) result: "
				+ optionalCat1.map(Cat::getAge).orElse(0));
		System.out.println("\toptionalCat1.map(cat -> cat.getAge() + cat.getAge()) result: "
				+ optionalCat1.map(cat -> "cat.getAge() " + cat.getAge()));
		System.out.println("\toptionalCat1.map(cat -> cat.getAge() + cat.getAge()).orElse(\"You aint got no cats!\") result: "
				+ optionalCat1.map(cat -> "cat.getAge() " + cat.getAge()).orElse("You aint got no cats!"));
		
		System.out.println("\n\toptionalCat1: " + optionalCat1);
	}

	private static Cat findCatByName(String name) {
		Cat foundCat = new Cat(name, 4);
		foundCat = null;
		return foundCat;
	}

	private static Optional<Cat> findOptionalCatByName(String name) {
		Cat foundCat = new Cat(name, 4);
//		foundCat = null;
		System.out.println(
				"\tTo put the Cat in the Optional box use the Optional.ofNullable(T value) method, which returns the Optional object."
						+ "\n\tLooks like: \"return Optional.ofNullable(foundCat);\"");
		return Optional.ofNullable(foundCat);
	}
}
