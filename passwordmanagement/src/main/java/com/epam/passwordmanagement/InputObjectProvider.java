package com.epam.passwordmanagement;

import java.util.Scanner;

public class InputObjectProvider {
	private static Scanner sc=new Scanner(new UnClosableDecorator(System.in));
	InputObjectProvider() {
	throw new IllegalStateException("utility class");
}
public static Scanner getSc() {
	return sc;
}

}
