package ua.nure.starodubets.SummaryTask4.service.impl;

import ua.nure.starodubets.SummaryTask4.model.User;
import ua.nure.starodubets.SummaryTask4.service.AuthorizationService;

/**
 *
 *
 * @author Ihor Starodubets
 *
 */
public class AuthorizationServiceImpl implements AuthorizationService {

	private static final int ADMIN_ROLE_ID = 1;
	private static final int TEACHER_ROLE_ID = 2;
	private static final int STUDENT_ROLE_ID = 3;

	@Override
	public boolean isAdmin(User user) {
		if(user.getRoleId() == ADMIN_ROLE_ID) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isTeacher(User user) {
		if(user.getRoleId() == TEACHER_ROLE_ID) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isStudent(User user) {
		if(user.getRoleId() == STUDENT_ROLE_ID) {
			return true;
		} else {
			return false;
		}
	}

}
