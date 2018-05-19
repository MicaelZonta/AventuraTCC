package br.com.projeto.aventura.recurso;

public enum RoleEnum {
	ADMIN, USER;

	public static String valueOf(RoleEnum role) {
		String value = "";

		if (role == RoleEnum.ADMIN) {
			value = "ROLE_ADMIN";
		}
		if (role == RoleEnum.USER) {
			value = "ROLE_USER";
		}

		return value;
	}
}