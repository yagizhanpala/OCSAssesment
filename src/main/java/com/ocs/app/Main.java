package com.ocs.app;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;

import com.ocs.business.ValidationBusiness;
import com.ocs.client.ClientApp;
import com.ocs.conf.Messages;

public class Main {

	public static void main(String[] args) throws ServletException, LifecycleException {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);

		try {
			System.out.println(Messages.inputRequest);

			String[] s = in.nextLine().split(" ");

			var client = new ClientApp();
			var vb = new ValidationBusiness();

			vb.validateInputAndFile(s);
			client.runApplication(s);
			
		} catch (IllegalArgumentException e) {
			System.out.println(Messages.notValidInputError);
		} catch (FileNotFoundException e) {
			System.out.println(Messages.fileCantFound);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(Messages.outOfBounds);
		} catch (Exception e) {
			System.out.println(Messages.unexpectedErrorAppeared);
		} finally {
			in.close();
		}
	}

}
