package com.Notes;

import org.testng.annotations.DataProvider;

public class Notes_TestData {
	@DataProvider(name = "create_note")
	public Object[][] createNotes() {
		// Two dimensional object
		// 2X2 , 2X3, 3X4
		return new Object[][] { { "Home", "Home_API", "Done for Home" }, { "Work", "Work_API", "Done for Work" },
				{ "Personal", "Personal_API", "Done for Personal" } };
	}

	@DataProvider(name = "create_note_negative_tcs")
	public Object[][] createNotes_negative() {
		// Two dimensional object
		return new Object[][] { { "Home", "Hom", "Done for Home", "Title must be between 4 and 100 characters" },
				{ "Work", "Work_API", "", "Description must be between 4 and 1000 characters" },
				{ "Work", "Work_API", "Des", "Description must be between 4 and 1000 characters" },
				{ "Personal", "", "Done for Personal", "Title must be between 4 and 100 characters" },
				{ "", "Title", "Done for Personal", "Category must be one of the categories: Home, Work, Personal" } };
	}

	@DataProvider(name = "All_Login")
	public Object[][] Login() {
		// Two dimensional object
		return new Object[][] { { "", "1234567890", "A valid email address is required" },
				{ "anurag@xyz.com", "", "Password must be between 6 and 30 characters" },
				{ "anurag@xyz", "1234567890", "A valid email address is required" },
				{ "", "", "A valid email address is required" },
				{ "wqewd", "1234567890", "A valid email address is required" },
				{ "anurag@xyz", "1234567", "A valid email address is required" },
				{ "anurag", "1234567", "A valid email address is required" },
				{ "anurag@xyz.com", "1234567890", "Login successful" },
				

		};
	}
	@DataProvider(name = "All_Soap_Requests_tcs")
	public Object[][] All_Soap_Requests() {
		// Two dimensional object
		return new Object[][] { {"AddResult", "Add.xml","10" },
				{ "DivideResult", "Divide.xml", "3" },
				{ "MultiplyResult", "Multiply.xml", "18" },
				{ "SubtractResult", "Subtract.xml","10" },
				
				

		};
	}}


