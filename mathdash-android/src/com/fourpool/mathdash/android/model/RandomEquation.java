package com.fourpool.mathdash.android.model;

import java.util.Random;

public class RandomEquation {

	public String equation;
	public int answer;
	public int answer1;
	public int answer2;
	public int answer3;
	public int answer4;

	private char[] operators = new char[] { '+', '-', '*', '/' };

	public RandomEquation() {
		Random random = new Random();
		int r = random.nextInt(4);
		char operator = operators[r];

		int first = random.nextInt(15);
		int second = random.nextInt(15);

		equation = first + " " + operator + " " + second;

		switch (operator) {
		case '+':
			answer = first + second;
			break;
		case '-':
			answer = first - second;
			break;
		case '*':
			answer = first * second;
			break;
		case '/':
			while (first == 0 && second == 0) {
				first = random.nextInt(15);
				second = random.nextInt(15);
			}

			int dividend = first * second;

			if (first == 0) {
				equation = dividend + " / " + second;
				answer = first;
			} else {
				equation = dividend + " / " + first;
				answer = dividend / first;
			}
			break;
		}

		int[] answers = new int[4];
		int randomIndex = random.nextInt(4);
		answers[randomIndex] = answer;
		int offset = 1;

		while (randomIndex - offset >= 0) {
			answers[randomIndex - offset] = answer - offset;
			offset++;
		}

		offset = 1;
		while (randomIndex + offset < 4) {
			answers[randomIndex + offset] = answer + offset;
			offset++;
		}

		answer1 = answers[0];
		answer2 = answers[1];
		answer3 = answers[2];
		answer4 = answers[3];
	}
}
