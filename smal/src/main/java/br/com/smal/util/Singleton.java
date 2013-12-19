package br.com.smal.util;

public class Singleton<T> {

	private static Object instance = null;

	private Singleton() {
	}

	private void checkInstance(Class<T> c) {
		if (instance == null) {
			try {
				instance = (Object) c.getClass().newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public T getInstance(Class<T> c) {
		checkInstance(c);

		return (T) instance;
	}
}
