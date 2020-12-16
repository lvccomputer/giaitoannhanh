package com.hungcd.giaitoannhanh.math;

public class Question implements Comparable<Question> {

	private Integer equationId;
	private String equation;
	private Boolean answer;
	private Boolean correct;

	public Question() {
	}

	public Question(Integer equationId, String equation, Boolean answer, Boolean correct) {
		this.equationId = equationId;
		this.equation = equation;
		this.answer = answer;
		this.correct = correct;
	}

	public Integer getEquationId() {
		return equationId;
	}

	public void setEquationId(Integer equationId) {
		this.equationId = equationId;
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public Boolean getAnswer() {
		return answer;
	}

	public void setAnswer(Boolean answer) {
		this.answer = answer;
	}

	public Boolean isCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	@Override
	public int compareTo(Question o) {
		return this.getEquationId().compareTo(o.getEquationId());
	}

	@Override
	public String toString() {
		return "Equation{" + "equationId=" + equationId + ", equation=" + equation + ", answer=" + answer + ", correct=" + correct + '}';
	}

}