import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.collections.ObservableList;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.geometry.*;
import java.util.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import java.io.*;
import java.math.*;

public class FSE extends Application {

	// class fields for starting menu scene1
	ArrayList<String> orderList = new ArrayList<String>(); // creates empty arrayList
	ArrayList<String> itemList = new ArrayList<String>(); // creates empty arrayList of strings
	ArrayList<Double> priceList = new ArrayList<Double>(); // creates empty arrayList of prices

	Scene scene1;
	Button btnPizzaType; // when pressed switch to pizza type menu
	Button btnToppings; // when pressed switch to toppings menu
	Button btnDrinks;// when pressed switch to drinks menu
	Button btnMain1, btnMain2, btnMain3;
	TextField txtName;
	TextField txtPhone;
	TextField txtAddress;

	TextField txtDetails;
	Button btnDetails;

	Label lblDetails, lblName, lblPhone, lblAddress;
	Button btnOrder;

	// class fields for pizza types scene2
	Scene scene2;
	Label lblPizzaType;
	CheckBox chkNormal, chkHawaiian, chkGreek, chkDetroit, chkNY;

	ToggleGroup sizeGroup1, sizeGroup2, sizeGroup3, sizeGroup4, sizeGroup5;
	ToggleGroup sizeGroupA, sizeGroupB, sizeGroupC, sizeGroupD, sizeGroupE;
	Label lblNormal, lblHawaiian, lblGreek, lblDetroit, lblNY;
	Label lblCrust1, lblCrust2, lblCrust3, lblCrust4, lblCrust5;

	RadioButton rbSmall1, rbMedium1, rbLarge1, rbThin1, rbThick1; // sizes for normal
	RadioButton rbSmall2, rbMedium2, rbLarge2, rbThin2, rbThick2; // sizes for hawaiian
	RadioButton rbSmall3, rbMedium3, rbLarge3, rbThin3, rbThick3; // sizes for greek
	RadioButton rbSmall4, rbMedium4, rbLarge4, rbThin4, rbThick4; // sizes for detroit
	RadioButton rbSmall5, rbMedium5, rbLarge5, rbThin5, rbThick5; // sizes for new york

	// class fields for toppings scene3
	Scene scene3;
	Label lblToppings;
	RadioButton rbCheese, rbPepperoni, rbHam, rbOlives, rbMozzarella, rbBeef, rbMushroom, rbPeppers, rbPineapple;

	// class fields for drinks scene4
	Scene scene4;
	Label lblDrinks;
	RadioButton rbCoke, rbPepsi, rbGinger, rbRoot, rbLemonade, rbWater;

	// class field for the stage
	Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		String[] price;

		Scanner inFile = new Scanner(new BufferedReader(new FileReader("Prices.txt")));
		// step 1 read everything
		while (inFile.hasNextLine()) { // reading 1 line at a time
			String line = inFile.nextLine();
			price = line.split(" ");
			itemList.add(price[0]);
			priceList.add(Double.parseDouble(price[1])); // adding item and price at the same time
		}
		itemList.add("Thin");
		itemList.add("Thick");
		priceList.add(0.0);
		priceList.add(0.0);

		inFile.close();

		stage = primaryStage;

		// building main menu scene1
		btnPizzaType = new Button("Pizza Types");
		btnPizzaType.setFont(new Font(40));
		btnPizzaType.setPrefWidth(350);
		btnPizzaType.setPrefHeight(135);

		btnOrder = new Button("Order");
		btnOrder.setFont(new Font(15));
		btnOrder.setPrefWidth(80);
		btnOrder.setPrefHeight(50);

		// label for name
		Label lblName = new Label("Name:");
		lblName.setMinWidth(50);
		lblName.setFont(new Font(15));

		Label lblDetails = new Label("Name:");
		lblDetails.setMinWidth(50);
		lblDetails.setFont(new Font(15));

		btnDetails = new Button("Details");
		btnDetails.setFont(new Font(15));

		btnDetails.setOnAction(e -> {
			try {
				btnDetails_Click();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});

		txtDetails = new TextField();
		txtDetails.setMinWidth(225);
		txtDetails.setMaxWidth(225);
		txtDetails.setFont(new Font(15));
		txtDetails.setPromptText("Enter your full name");

		// create the text field for the name
		txtName = new TextField();
		txtName.setMinWidth(225);
		txtName.setMaxWidth(225);
		txtName.setFont(new Font(15));
		txtName.setPromptText("Enter your name");

		// label for phone
		Label lblPhone = new Label("Phone Number:");
		lblPhone.setMinWidth(50);
		lblPhone.setFont(new Font(15));

		// create the text field for the phone
		txtPhone = new TextField();
		txtPhone.setMinWidth(225);
		txtPhone.setMaxWidth(225);
		txtPhone.setFont(new Font(15));
		txtPhone.setPromptText("Enter your phone number");

		// label for address
		Label lblAddress = new Label("Address:");
		lblAddress.setMinWidth(50);
		lblAddress.setFont(new Font(15));

		// create the text field for the address
		txtAddress = new TextField();
		txtAddress.setMinWidth(225);
		txtAddress.setMaxWidth(225);
		txtAddress.setFont(new Font(15));
		txtAddress.setPromptText("Enter your address");

		// set action for buttons pizza type
		btnPizzaType.setOnAction(e -> btnPizzaType_Click());

		btnToppings = new Button("Toppings");
		btnToppings.setFont(new Font(40));
		btnToppings.setPrefWidth(350);
		btnToppings.setPrefHeight(135);

		// set action for buttons toppings
		btnToppings.setOnAction(e -> btnToppings_Click());

		btnDrinks = new Button("Drinks");
		btnDrinks.setFont(new Font(40));
		btnDrinks.setPrefWidth(350);
		btnDrinks.setPrefHeight(135);

		// set action for buttons drinks
		btnDrinks.setOnAction(e -> btnDrinks_Click());

		// button for ordering
		btnOrder.setOnAction(e -> {
			try {
				btnOrder_Click();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		HBox pane1 = new HBox(30, btnPizzaType, btnToppings, btnDrinks);
		pane1.setAlignment(Pos.TOP_CENTER);

		HBox paneDetails = new HBox(15, lblDetails, txtDetails);
		paneDetails.setAlignment(Pos.TOP_LEFT);

		VBox panebtnDetails = new VBox(5, btnDetails);
		panebtnDetails.setAlignment(Pos.TOP_LEFT);

		VBox paneDetails2 = new VBox(20, paneDetails, panebtnDetails);

		HBox paneName = new HBox(15, lblName, txtName);
		paneName.setAlignment(Pos.CENTER);
		HBox panePhone = new HBox(15, lblPhone, txtPhone);
		panePhone.setAlignment(Pos.CENTER);
		HBox paneAddress = new HBox(15, lblAddress, txtAddress);
		paneAddress.setAlignment(Pos.CENTER);

		VBox paneOrder = new VBox(10, btnOrder);
		paneOrder.setAlignment(Pos.CENTER);

		VBox pane2 = new VBox(15, paneName, panePhone, paneAddress, paneOrder, paneDetails2);

		VBox layout1 = new VBox(100, pane1, pane2);
		// main menu
		scene1 = new Scene(layout1, 1200, 600);

		// build scene2 PizzaType
		lblPizzaType = new Label("Pizza Types");
		lblPizzaType.setFont(new Font(40));
		btnMain1 = new Button("Done");
		btnMain1.setOnAction(e -> btnMain1_Click());
		btnMain1.setPrefWidth(110);
		btnMain1.setPrefHeight(25);

		// creating check box and label for types of pizza

		// normal pizza
		chkNormal = new CheckBox("Normal");
		chkNormal.setFont(new Font(20));
		lblNormal = new Label("Sizes");
		lblNormal.setFont(new Font(15));

		lblCrust1 = new Label("Crust");
		lblCrust1.setFont(new Font(15));

		// radio button for sizes and crust
		sizeGroup1 = new ToggleGroup();
		sizeGroupA = new ToggleGroup();

		rbSmall1 = new RadioButton("Small");
		rbMedium1 = new RadioButton("Medium");
		rbLarge1 = new RadioButton("Large");

		rbThin1 = new RadioButton("Thin");
		rbThick1 = new RadioButton("Thick");

		rbSmall1.setToggleGroup(sizeGroup1);
		rbMedium1.setToggleGroup(sizeGroup1);
		rbLarge1.setToggleGroup(sizeGroup1);

		rbThin1.setToggleGroup(sizeGroupA);
		rbThick1.setToggleGroup(sizeGroupA);

		chkNormal.setOnAction(e -> chkNormal_Click());

		// hawaiian pizza
		chkHawaiian = new CheckBox("Hawaiian");
		chkHawaiian.setFont(new Font(20));
		lblHawaiian = new Label("Sizes");
		lblHawaiian.setFont(new Font(15));

		lblCrust2 = new Label("Crust");
		lblCrust2.setFont(new Font(15));

		// radio button for sizes
		sizeGroup2 = new ToggleGroup();
		sizeGroupB = new ToggleGroup();

		rbSmall2 = new RadioButton("Small");
		rbMedium2 = new RadioButton("Medium");
		rbLarge2 = new RadioButton("Large");

		rbThin2 = new RadioButton("Thin");
		rbThick2 = new RadioButton("Thick");

		rbSmall2.setToggleGroup(sizeGroup2);
		rbMedium2.setToggleGroup(sizeGroup2);
		rbLarge2.setToggleGroup(sizeGroup2);

		rbThin2.setToggleGroup(sizeGroupB);
		rbThick2.setToggleGroup(sizeGroupB);

		chkHawaiian.setOnAction(e -> chkHawaiian_Click());

		// greek pizza
		chkGreek = new CheckBox("Greek");
		chkGreek.setFont(new Font(20));
		lblGreek = new Label("Sizes");
		lblGreek.setFont(new Font(15));

		lblCrust3 = new Label("Crust");
		lblCrust3.setFont(new Font(15));

		// radio button for sizes
		sizeGroup3 = new ToggleGroup();
		sizeGroupC = new ToggleGroup();

		rbSmall3 = new RadioButton("Small");
		rbMedium3 = new RadioButton("Medium");
		rbLarge3 = new RadioButton("Large");

		rbThin3 = new RadioButton("Thin");
		rbThick3 = new RadioButton("Thick");

		rbSmall3.setToggleGroup(sizeGroup3);
		rbMedium3.setToggleGroup(sizeGroup3);
		rbLarge3.setToggleGroup(sizeGroup3);

		rbThin3.setToggleGroup(sizeGroupC);
		rbThick3.setToggleGroup(sizeGroupC);

		chkGreek.setOnAction(e -> chkGreek_Click());

		// detroit pizza
		chkDetroit = new CheckBox("Detroit");
		chkDetroit.setFont(new Font(20));
		lblDetroit = new Label("Sizes");
		lblDetroit.setFont(new Font(15));

		lblCrust4 = new Label("Crust");
		lblCrust4.setFont(new Font(15));

		// radio button for sizes
		sizeGroup4 = new ToggleGroup();
		sizeGroupD = new ToggleGroup();

		rbSmall4 = new RadioButton("Small");
		rbMedium4 = new RadioButton("Medium");
		rbLarge4 = new RadioButton("Large");

		rbThin4 = new RadioButton("Thin");
		rbThick4 = new RadioButton("Thick");

		rbSmall4.setToggleGroup(sizeGroup3);
		rbMedium4.setToggleGroup(sizeGroup3);
		rbLarge4.setToggleGroup(sizeGroup3);

		rbThin4.setToggleGroup(sizeGroupD);
		rbThick4.setToggleGroup(sizeGroupD);

		chkDetroit.setOnAction(e -> chkDetroit_Click());

		// new york pizza
		chkNY = new CheckBox("New York");
		chkNY.setFont(new Font(20));
		lblNY = new Label("Sizes");
		lblNY.setFont(new Font(15));

		lblCrust5 = new Label("Crust");
		lblCrust5.setFont(new Font(15));

		// radio button for sizes
		sizeGroup5 = new ToggleGroup();
		sizeGroupE = new ToggleGroup();

		rbSmall5 = new RadioButton("Small");
		rbMedium5 = new RadioButton("Medium");
		rbLarge5 = new RadioButton("Large");

		rbThin5 = new RadioButton("Thin");
		rbThick5 = new RadioButton("Thick");

		rbSmall5.setToggleGroup(sizeGroup5);
		rbMedium5.setToggleGroup(sizeGroup5);
		rbLarge5.setToggleGroup(sizeGroup5);

		rbThin5.setToggleGroup(sizeGroupE);
		rbThick5.setToggleGroup(sizeGroupE);

		chkNY.setOnAction(e -> chkNY_Click());

		// pizza images
		Image imgNormal = new Image("file:C:\\temp\\images\\Normal.png");
		Image imgHawaiian = new Image("file:C:\\temp\\images\\Hawaiian.jpg");
		Image imgGreek = new Image("file:C:\\temp\\images\\Greek.jpg");
		Image imgDetroit = new Image("file:C:\\temp\\images\\Detroit.jpg");
		Image imgNY = new Image("file:C:\\temp\\images\\NY.jpg");

		ImageView iview1 = new ImageView(imgNormal);
		iview1.setFitWidth(150);
		iview1.setFitHeight(125);
		iview1.setPreserveRatio(true);

		ImageView iview2 = new ImageView(imgHawaiian);
		iview2.setFitWidth(200);
		iview2.setFitHeight(150);
		iview2.setPreserveRatio(true);

		ImageView iview3 = new ImageView(imgGreek);
		iview3.setFitWidth(150);
		iview3.setFitHeight(125);
		iview3.setPreserveRatio(true);

		ImageView iview4 = new ImageView(imgDetroit);
		iview4.setFitWidth(150);
		iview4.setFitHeight(125);
		iview4.setPreserveRatio(true);

		ImageView iview5 = new ImageView(imgNY);
		iview5.setFitWidth(150);
		iview5.setFitHeight(125);
		iview5.setPreserveRatio(true);

		// layout for all the buttons and labels in scene 2
		HBox aPane1 = new HBox(5, lblPizzaType);
		HBox aPane2 = new HBox(5, btnMain1);

		HBox aPaneA = new HBox(10, lblNormal, lblCrust1);

		VBox aPaneA2 = new VBox(5, rbSmall1, rbMedium1, rbLarge1);

		VBox aPaneA3 = new VBox(5, rbThin1, rbThick1);

		HBox aPaneA4 = new HBox(5, aPaneA2, aPaneA3);

		VBox aPane3 = new VBox(5, chkNormal, aPaneA, aPaneA4);

		HBox aPaneB = new HBox(10, lblHawaiian, lblCrust2);

		VBox aPaneB2 = new VBox(5, rbSmall2, rbMedium2, rbLarge2);

		VBox aPaneB3 = new VBox(5, rbThin2, rbThick2);

		HBox aPaneB4 = new HBox(5, aPaneB2, aPaneB3);

		VBox aPane4 = new VBox(5, chkHawaiian, aPaneB, aPaneB4);

		HBox aPaneC = new HBox(10, lblGreek, lblCrust3);

		VBox aPaneC2 = new VBox(5, rbSmall3, rbMedium3, rbLarge3);

		VBox aPaneC3 = new VBox(5, rbThin3, rbThick3);

		HBox aPaneC4 = new HBox(5, aPaneC2, aPaneC3);

		VBox aPane5 = new VBox(5, chkGreek, aPaneC, aPaneC4);

		HBox aPaneD = new HBox(10, lblDetroit, lblCrust4);

		VBox aPaneD2 = new VBox(5, rbSmall4, rbMedium4, rbLarge4);

		VBox aPaneD3 = new VBox(5, rbThin4, rbThick4);

		HBox aPaneD4 = new HBox(5, aPaneD2, aPaneD3);

		VBox aPane6 = new VBox(5, chkDetroit, aPaneD, aPaneD4);

		HBox aPaneE = new HBox(10, lblNY, lblCrust5);

		VBox aPaneE2 = new VBox(5, rbSmall5, rbMedium5, rbLarge5);

		VBox aPaneE3 = new VBox(5, rbThin5, rbThick5);

		HBox aPaneE4 = new HBox(5, aPaneE2, aPaneE3);

		VBox aPane7 = new VBox(5, chkNY, aPaneE, aPaneE4);

		VBox aPane8 = new VBox(5, aPane1, aPane2, aPane3, aPane4, aPane5, aPane6, aPane7);

		VBox imgPane1 = new VBox(50, iview1, iview2, iview3, iview4, iview5);

		HBox aPane9 = new HBox(5, aPane8, imgPane1);

		aPane9.setAlignment(Pos.BOTTOM_LEFT);

		VBox layout2 = new VBox(30, aPane9);

		scene2 = new Scene(layout2, 500, 800);

		// initial type of pizza chosen normal
		chkNormal.setSelected(true);
		chkNormal.setDisable(true);
		rbSmall1.setSelected(true);
		rbThin1.setSelected(true);

		rbSmall1.setDisable(false);
		rbMedium1.setDisable(false);
		rbLarge1.setDisable(false);
		rbThick1.setDisable(false);

		rbSmall2.setDisable(true);
		rbMedium2.setDisable(true);
		rbLarge2.setDisable(true);
		rbThin2.setDisable(true);
		rbThick2.setDisable(true);

		rbSmall3.setDisable(true);
		rbMedium3.setDisable(true);
		rbLarge3.setDisable(true);
		rbThin3.setDisable(true);
		rbThick3.setDisable(true);

		rbSmall4.setDisable(true);
		rbMedium4.setDisable(true);
		rbLarge4.setDisable(true);
		rbThin4.setDisable(true);
		rbThick4.setDisable(true);

		rbSmall5.setDisable(true);
		rbMedium5.setDisable(true);
		rbLarge5.setDisable(true);
		rbThin5.setDisable(true);
		rbThick5.setDisable(true);

		// build scene3 Toppings
		lblToppings = new Label("Toppings");
		lblToppings.setFont(new Font(40));
		btnMain2 = new Button("Done");
		btnMain2.setOnAction(e -> btnMain2_Click());
		btnMain2.setPrefWidth(110);
		btnMain2.setPrefHeight(25);

		// radio buttons for toppings rbCheese, rbPepperoni, rbHam, rbOlives,
		// rbMozzarella, rbBeef, rbMushroom, rbPeppers, rbPineapple

		rbCheese = new RadioButton("Cheese");
		rbCheese.setFont(new Font(20));

		rbPepperoni = new RadioButton("Pepperoni");
		rbPepperoni.setFont(new Font(20));

		rbHam = new RadioButton("Ham");
		rbHam.setFont(new Font(20));

		rbOlives = new RadioButton("Olives");
		rbOlives.setFont(new Font(20));

		rbMozzarella = new RadioButton("Mozzarella");
		rbMozzarella.setFont(new Font(20));

		rbBeef = new RadioButton("Beef");
		rbBeef.setFont(new Font(20));

		rbMushroom = new RadioButton("Mushrooms");
		rbMushroom.setFont(new Font(20));

		rbPeppers = new RadioButton("Peppers");
		rbPeppers.setFont(new Font(20));

		rbPineapple = new RadioButton("Pineapple");
		rbPineapple.setFont(new Font(20));

		HBox bPane1 = new HBox(5, lblToppings);
		HBox bPane2 = new HBox(5, btnMain2);
		bPane2.setAlignment(Pos.BOTTOM_RIGHT);

		VBox bPane3 = new VBox(10, rbCheese, rbPepperoni, rbHam, rbOlives, rbMozzarella, rbBeef, rbMushroom, rbPeppers,
				rbPineapple);

		VBox layout3 = new VBox(10, bPane1, bPane2, bPane3);
		scene3 = new Scene(layout3, 500, 550);

		// build scene4 Drinks
		lblDrinks = new Label("Drinks");
		lblDrinks.setFont(new Font(40));
		btnMain3 = new Button("Done");
		btnMain3.setOnAction(e -> btnMain3_Click());
		btnMain3.setPrefWidth(110);
		btnMain3.setPrefHeight(25);

		// radio buttons for drinks rbCoke, rbPepsi, rbGinger, rbRoot, rbLemonade,
		// rbWater

		rbCoke = new RadioButton("Coca Cola");
		rbCoke.setFont(new Font(20));

		rbPepsi = new RadioButton("Pepsi");
		rbPepsi.setFont(new Font(20));

		rbGinger = new RadioButton("Ginger Ale");
		rbGinger.setFont(new Font(20));

		rbRoot = new RadioButton("Root Beer");
		rbRoot.setFont(new Font(20));

		rbLemonade = new RadioButton("Lemonade");
		rbLemonade.setFont(new Font(20));

		rbWater = new RadioButton("Water");
		rbWater.setFont(new Font(20));

		HBox cPane1 = new HBox(5, lblDrinks);
		HBox cPane2 = new HBox(5, btnMain3);
		cPane2.setAlignment(Pos.BOTTOM_RIGHT);

		VBox cPane3 = new VBox(5, rbCoke, rbPepsi, rbGinger, rbRoot, rbLemonade, rbWater);

		VBox layout4 = new VBox(10, cPane1, cPane2, cPane3);

		scene4 = new Scene(layout4, 500, 550);

		primaryStage.setScene(scene1);
		primaryStage.setTitle("Pizza Order FSE");
		primaryStage.show();

	}

	private void btnDetails_Click() throws IOException {
		String name = "";
		String details = "";
		Scanner inFile = new Scanner(new BufferedReader(new FileReader("OrderDetails.txt")));
		name = txtDetails.getText();

		while (inFile.hasNextLine()) { // reading 1 line at a time
			String line = inFile.nextLine();
			if (line.equals(name)) {
				inFile.nextLine();
				inFile.nextLine();
				while (true) {
					if (inFile.hasNextLine()) {
						line = inFile.nextLine();

						if (itemList.contains(line)) {
							details += line + "\n";

						} else {
							details += line + "\n";

							break;
						}
					}
				}

			}
		}
		MessageBox.show(details, "Your Order Details");

	}
//button for ordering
	private void btnOrder_Click() throws IOException {

		ArrayList<String> orderList = new ArrayList<String>(); // creates empty arrayList

		String pizzaType = "";
		String size = "";
		String crust = "";
		String toppings = "";
		String drinks = "";

		if (chkNormal.isSelected()) {
			pizzaType = "Normal";

			if (rbSmall1.isSelected()) {
				size = "Small";
			} else if (rbMedium1.isSelected()) {
				size = "Medium";
			} else if (rbLarge1.isSelected()) {
				size = "Large";
			}
			if (rbThin1.isSelected()) {
				crust = "Thin";
			} else if (rbThick1.isSelected()) {
				crust = "Thick";
			}

		} else if (chkHawaiian.isSelected()) {
			pizzaType = "Hawaiian";

			if (rbSmall2.isSelected()) {
				size = "Small";
			} else if (rbMedium2.isSelected()) {
				size = "Medium";
			} else if (rbLarge2.isSelected()) {
				size = "Large";
			}
			if (rbThin2.isSelected()) {
				crust = "Thin";
			} else if (rbThick2.isSelected()) {
				crust = "Thick";
			}

		} else if (chkGreek.isSelected()) {
			pizzaType = "Greek";

			if (rbSmall3.isSelected()) {
				size = "Small";
			} else if (rbMedium3.isSelected()) {
				size = "Medium";
			} else if (rbLarge3.isSelected()) {
				size = "Large";
			}
			if (rbThin3.isSelected()) {
				crust = "Thin";
			} else if (rbThick3.isSelected()) {
				crust = "Thick";
			}

		} else if (chkDetroit.isSelected()) {
			pizzaType = "Detroit";

			if (rbSmall4.isSelected()) {
				size = "Small";
			} else if (rbMedium4.isSelected()) {
				size = "Medium";
			} else if (rbLarge4.isSelected()) {
				size = "Large";
			}
			if (rbThin4.isSelected()) {
				crust = "Thin";
			} else if (rbThick4.isSelected()) {
				crust = "Thick";
			}

		} else if (chkNY.isSelected()) {
			pizzaType = "NY";

			if (rbSmall5.isSelected()) {
				size = "Small";
			} else if (rbMedium5.isSelected()) {
				size = "Medium";
			} else if (rbLarge5.isSelected()) {
				size = "Large";
			}
			if (rbThin5.isSelected()) {
				crust = "Thin";
			} else if (rbThick5.isSelected()) {
				crust = "Thick";
			}

		}
		orderList.add(pizzaType);
		orderList.add(size);
		orderList.add(crust);

		if (rbCheese.isSelected()) {
			toppings += "Cheese\n";
			orderList.add("Cheese");
		}
		if (rbPepperoni.isSelected()) {
			toppings += "Pepperoni\n";
			orderList.add("Pepperoni");
		}
		if (rbHam.isSelected()) {
			toppings += "Ham\n";
			orderList.add("Ham");
		}
		if (rbOlives.isSelected()) {
			toppings += "Olives\n";
			orderList.add("Olives");
		}
		if (rbMozzarella.isSelected()) {
			toppings += "Mozzarella\n";
			orderList.add("Mozzarella");
		}
		if (rbBeef.isSelected()) {
			toppings += "Beef\n";
			orderList.add("Beef");
		}
		if (rbMushroom.isSelected()) {
			toppings += "Mushrooms\n";
			orderList.add("Mushrooms");
		}
		if (rbPeppers.isSelected()) {
			toppings += "Peppers\n";
			orderList.add("Peppers");
		}
		if (rbPineapple.isSelected()) {
			toppings += "Pineapple\n";
			orderList.add("Pineapple");
		}

		if (rbCoke.isSelected()) {
			drinks += "CocaCola\n";
			orderList.add("CocaCola");
		}
		if (rbPepsi.isSelected()) {
			drinks += "Pepsi\n";
			orderList.add("Pepsi");
		}
		if (rbGinger.isSelected()) {
			drinks += "GingerAle\n";
			orderList.add("GingerAle");
		}
		if (rbRoot.isSelected()) {
			drinks += "RootBeer\n";
			orderList.add("RootBeer");
		}
		if (rbLemonade.isSelected()) {
			drinks += "Lemonade\n";
			orderList.add("Lemonade");
		}
		if (rbWater.isSelected()) {
			drinks += "Water\n";
			orderList.add("Water");
		}
		
		//loops through the orderList to get the total price
		double total = 0;

		for (int i = 0; i < orderList.size(); i++) {

			for (int j = 0; j < itemList.size(); j++) {

				if (orderList.get(i).equals(itemList.get(j))) {
					total += priceList.get(j);

				}

			}

		}

		//the textfields can not be empty
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("OrderDetails.txt", true)));
		String errMessage = "";

		if (txtName.getText().length() == 0) {
			errMessage += "Name is a required field\n";
		}

		if (txtPhone.getText().length() == 0) {
			errMessage += "Phone is a required field\n";
		}

		if (txtAddress.getText().length() == 0) {
			errMessage += "Address is a required field";
		}

		// prints out the details in a text file
		total = Math.round(total * 100.0) / 100.0;
		if (errMessage.length() == 0) {
			outFile.println(txtName.getText());
			outFile.println(txtPhone.getText());
			outFile.println(txtAddress.getText());
			outFile.println(pizzaType);
			outFile.println(size);
			outFile.println(crust);
			outFile.print(toppings);
			outFile.print(drinks);
			outFile.println(total);

		} else {
			MessageBox.show(errMessage, "Incomplete Data");
		}

		outFile.close();
	}

	// scene switcher
	private void btnMain1_Click() {
		stage.setScene(scene1);
	}

	private void btnMain2_Click() {
		stage.setScene(scene1);
	}

	private void btnMain3_Click() {
		stage.setScene(scene1);
	}

	private void btnPizzaType_Click() {
		stage.setScene(scene2);
	}

	private void btnToppings_Click() {
		stage.setScene(scene3);
	}

	private void btnDrinks_Click() {
		stage.setScene(scene4);
	}

	// options for what types of pizza and disables the others if you chose 1 type
	private void chkNormal_Click() {
		if (chkNormal.isSelected()) {
			chkNY.setDisable(false);
			chkDetroit.setDisable(false);
			chkHawaiian.setDisable(false);
			chkNormal.setDisable(true);
			chkHawaiian.setDisable(false);
			chkHawaiian.setSelected(false);
			chkGreek.setSelected(false);
			chkGreek.setDisable(false);
			chkDetroit.setSelected(false);
			chkNY.setSelected(false);

			rbSmall1.setSelected(true);
			rbThin1.setSelected(true);

			rbSmall1.setDisable(false);
			rbMedium1.setDisable(false);
			rbLarge1.setDisable(false);
			rbThin1.setDisable(false);
			rbThick1.setDisable(false);

			rbSmall2.setDisable(true);
			rbMedium2.setDisable(true);
			rbLarge2.setDisable(true);
			rbThin2.setDisable(true);
			rbThick2.setDisable(true);

			rbSmall3.setDisable(true);
			rbMedium3.setDisable(true);
			rbLarge3.setDisable(true);
			rbThin3.setDisable(true);
			rbThick3.setDisable(true);

			rbSmall4.setDisable(true);
			rbMedium4.setDisable(true);
			rbLarge4.setDisable(true);
			rbThin4.setDisable(true);
			rbThick4.setDisable(true);

			rbSmall5.setDisable(true);
			rbMedium5.setDisable(true);
			rbLarge5.setDisable(true);
			rbThin5.setDisable(true);
			rbThick5.setDisable(true);

		}

	}

	private void chkHawaiian_Click() {
		if (chkHawaiian.isSelected()) {
			chkNY.setDisable(false);
			chkDetroit.setDisable(false);
			chkGreek.setDisable(false);
			chkHawaiian.setDisable(true);
			chkNormal.setDisable(false);
			chkNormal.setSelected(false);
			chkGreek.setSelected(false);
			chkDetroit.setSelected(false);
			chkNY.setSelected(false);

			rbSmall2.setSelected(true);
			rbThin2.setSelected(true);

			rbSmall1.setDisable(true);
			rbMedium1.setDisable(true);
			rbLarge1.setDisable(true);
			rbThin1.setDisable(true);
			rbThick1.setDisable(true);

			rbSmall2.setDisable(false);
			rbMedium2.setDisable(false);
			rbLarge2.setDisable(false);
			rbThin2.setDisable(false);
			rbThick2.setDisable(false);

			rbSmall3.setDisable(true);
			rbMedium3.setDisable(true);
			rbLarge3.setDisable(true);
			rbThin3.setDisable(true);
			rbThick3.setDisable(true);

			rbSmall4.setDisable(true);
			rbMedium4.setDisable(true);
			rbLarge4.setDisable(true);
			rbThin4.setDisable(true);
			rbThick4.setDisable(true);

			rbSmall5.setDisable(true);
			rbMedium5.setDisable(true);
			rbLarge5.setDisable(true);
			rbThin5.setDisable(true);
			rbThick5.setDisable(true);

		}

	}

	private void chkGreek_Click() {
		if (chkGreek.isSelected()) {
			chkNY.setDisable(false);
			chkDetroit.setDisable(false);
			chkGreek.setDisable(true);
			chkHawaiian.setDisable(false);
			chkNormal.setDisable(false);
			chkNormal.setSelected(false);
			chkHawaiian.setSelected(false);
			chkDetroit.setSelected(false);
			chkNY.setSelected(false);

			rbSmall3.setSelected(true);
			rbThin3.setSelected(true);

			rbSmall1.setDisable(true);
			rbMedium1.setDisable(true);
			rbLarge1.setDisable(true);
			rbThin1.setDisable(true);
			rbThick1.setDisable(true);

			rbSmall2.setDisable(true);
			rbMedium2.setDisable(true);
			rbLarge2.setDisable(true);
			rbThin2.setDisable(true);
			rbThick2.setDisable(true);

			rbSmall3.setDisable(false);
			rbMedium3.setDisable(false);
			rbLarge3.setDisable(false);
			rbThin3.setDisable(false);
			rbThick3.setDisable(false);

			rbSmall4.setDisable(true);
			rbMedium4.setDisable(true);
			rbLarge4.setDisable(true);
			rbThin4.setDisable(true);
			rbThick4.setDisable(true);

			rbSmall5.setDisable(true);
			rbMedium5.setDisable(true);
			rbLarge5.setDisable(true);
			rbThin5.setDisable(true);
			rbThick5.setDisable(true);

		}

	}

	private void chkDetroit_Click() {
		if (chkDetroit.isSelected()) {
			chkNY.setDisable(false);
			chkDetroit.setDisable(true);
			chkGreek.setDisable(false);
			chkHawaiian.setDisable(false);
			chkNormal.setDisable(false);
			chkNormal.setSelected(false);
			chkHawaiian.setSelected(false);
			chkGreek.setSelected(false);
			chkNY.setSelected(false);

			rbSmall4.setSelected(true);
			rbThin4.setSelected(true);

			rbSmall1.setDisable(true);
			rbMedium1.setDisable(true);
			rbLarge1.setDisable(true);
			rbThin1.setDisable(true);
			rbThick1.setDisable(true);

			rbSmall2.setDisable(true);
			rbMedium2.setDisable(true);
			rbLarge2.setDisable(true);
			rbThin2.setDisable(true);
			rbThick2.setDisable(true);

			rbSmall3.setDisable(true);
			rbMedium3.setDisable(true);
			rbLarge3.setDisable(true);
			rbThin3.setDisable(true);
			rbThick3.setDisable(true);

			rbSmall4.setDisable(false);
			rbMedium4.setDisable(false);
			rbLarge4.setDisable(false);
			rbThin4.setDisable(false);
			rbThick4.setDisable(false);

			rbSmall5.setDisable(true);
			rbMedium5.setDisable(true);
			rbLarge5.setDisable(true);
			rbThin5.setDisable(true);
			rbThick5.setDisable(true);

		}

	}

	private void chkNY_Click() {
		if (chkNY.isSelected()) {
			chkNY.setDisable(true);
			chkDetroit.setDisable(false);
			chkGreek.setDisable(false);
			chkHawaiian.setDisable(false);
			chkNormal.setDisable(false);
			chkDetroit.setSelected(false);
			chkNormal.setSelected(false);
			chkHawaiian.setSelected(false);
			chkGreek.setSelected(false);

			rbSmall5.setSelected(true);
			rbThin5.setSelected(true);

			rbSmall1.setDisable(true);
			rbMedium1.setDisable(true);
			rbLarge1.setDisable(true);
			rbThin1.setDisable(true);
			rbThick1.setDisable(true);

			rbSmall2.setDisable(true);
			rbMedium2.setDisable(true);
			rbLarge2.setDisable(true);
			rbThin2.setDisable(true);
			rbThick2.setDisable(true);

			rbSmall3.setDisable(true);
			rbMedium3.setDisable(true);
			rbLarge3.setDisable(true);
			rbThin3.setDisable(true);
			rbThick3.setDisable(true);

			rbSmall4.setDisable(true);
			rbMedium4.setDisable(true);
			rbLarge4.setDisable(true);
			rbThin4.setDisable(true);
			rbThick4.setDisable(true);

			rbSmall5.setDisable(false);
			rbMedium5.setDisable(false);
			rbLarge5.setDisable(false);
			rbThin5.setDisable(false);
			rbThick5.setDisable(false);

		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
