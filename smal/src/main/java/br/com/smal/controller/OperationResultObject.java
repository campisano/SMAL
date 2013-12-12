package br.com.smal.controller;

public class OperationResultObject<T> extends OperationResult {

	private T object;

	public OperationResultObject(boolean success, String message, T object) {
		super(success, message);
		this.object = object;
	}

	public T getObject() {
		return object;
	}
}
