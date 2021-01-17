package org.cargo.web.rest;

import java.io.IOException;
import java.lang.IllegalArgumentException;
import javax.servlet.http.HttpServletResponse;
import org.sculptor.framework.context.ServiceContext;
import org.sculptor.framework.context.ServiceContextStore;
import org.sculptor.framework.errorhandling.OptimisticLockingException;
import org.sculptor.framework.errorhandling.SystemException;
import org.sculptor.framework.errorhandling.ValidationException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Generated base class for implementation of RoutesResource.
 * <p>Make sure that subclass defines the following annotations:
 * <pre>
@org.springframework.stereotype.Controller
 * </pre>
 */
public abstract class RoutesResourceBase {

	public RoutesResourceBase() {
	}

	protected ServiceContext serviceContext() {
		return ServiceContextStore.get();
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

	/*
	 * @org.springframework.web.bind.annotation.RequestMapping(value =
	 * "/cargoes/{trackingID}/routes",
	 * method=org.springframework.web.bind.annotation.RequestMethod.GET) public void
	 * assignCargoToRoute()
	 */
	public abstract void assignCargoToRoute();

	/*
	 * @org.springframework.web.bind.annotation.RequestMapping(value =
	 * "/cargoes/{trackingID}/routes",
	 * method=org.springframework.web.bind.annotation.RequestMethod.POST) public
	 * void requestPossibleRoutesForCargo()
	 */
	public abstract void requestPossibleRoutesForCargo();

	/**
	 * This method is needed for form data POST. Delegates to {@link #requestPossibleRoutesForCargo}
	 */
	@RequestMapping(value = "/cargoes/{trackingID}/routes", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
	public void requestPossibleRoutesForCargoFromForm() {
		requestPossibleRoutesForCargo();
	}

	@ExceptionHandler
	public void handleException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler
	public void handleException(ValidationException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler
	public void handleException(SystemException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
	}

	@ExceptionHandler
	public void handleException(OptimisticLockingException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.CONFLICT.value(), e.getMessage());
	}

}
