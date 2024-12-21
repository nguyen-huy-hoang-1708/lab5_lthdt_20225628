package hust.soict.dsai.aims;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class Aims {

	public static void showMenu() {
		System.out.println("AIMS: ");
		System.out.println("--------------------------------");
		System.out.println("1. View store");
		System.out.println("2. Update store");
		System.out.println("3. See current cart");
		System.out.println("0. Exit");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3");
	}

	public static void storeMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. See a media’s details");
		System.out.println("2. Add a media to cart");
		System.out.println("3. Play a media");
		System.out.println("4. See current cart");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4");
	}

	public static void sortMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Sort by Title");
		System.out.println("2. Sort by Cost");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 1-2");
	}

	public static void filterMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Filter by ID");
		System.out.println("2. Filter by Title");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 1-2");
	}

	public static void updateStoreMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Add a media to the store");
		System.out.println("2. Remove media from the store");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
	}

	public static void mediaDetailsMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Add to cart");
		System.out.println("2. Play");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
	}

	public static void cartMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Filter medias in cart");
		System.out.println("2. Sort medias in cart");
		System.out.println("3. Remove media from cart");
		System.out.println("4. Play a media");
		System.out.println("5. Place order");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4-5");
	}

	public static void playMedia(Media m) throws PlayerException {
		if (m instanceof DigitalVideoDisc) {
			((DigitalVideoDisc) m).play();
		}
		else if (m instanceof CompactDisc) {
			((CompactDisc) m).play();
		}
		else {
			System.out.println("This media type cannot be played!");
		}
	}

	public static void testList(Store store) {
		DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		store.addMedia(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars",
				"Science Fiction", "George Lucas", 87, 24.95f);
		store.addMedia(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladin",
				"Animation", 18.99f);
		store.addMedia(dvd3);

		List<String> authors1 = new ArrayList<>();
		authors1.add("Than Quang Khoat");
		authors1.add("Dinh Viet Sang");
		Book book1 = new Book(4, "Intro to A.I", "Math & Science", 25.5f, authors1);

		List<String> authors2 = new ArrayList<>();
		authors2.add("Trinh Tuan Dat");
		authors2.add("Nguyen Thi Thu Trang");
		Book book2 = new Book(5, "OOP Techniques", "Computer Science", 29.5f, authors2);

		store.addMedia(dvd1);
		store.addMedia(dvd2);
		store.addMedia(dvd3);
		store.addMedia(book1);
		store.addMedia(book2);

	}

	public static void main(String[] args) throws PlayerException, LimitExceededException {
		/*
		Cart anOrder = new Cart();
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		anOrder.addDigitalVideoDisc(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
				"Science Fiction", "George Lucas", 87, 24.95f);
		anOrder.addDigitalVideoDisc(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
				"Animation", 18.99f);
		anOrder.addDigitalVideoDisc(dvd3);

		System.out.println("The total cost is: " + anOrder.totalCost());

		anOrder.removeMedia(dvd1);
		System.out.println("The total cost is: " + anOrder.totalCost());
		*/
		Store store = new Store();
		Cart cart = new Cart();
		testList(store);
		String title;
		Media mediaInStore, mediaInCart;
		int index = 6;

		Scanner sc = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		while (true) {
			boolean isExit = false;
			// Display App menu
			showMenu();
			int option = sc.nextInt();
			switch(option) {
			case 1:
				// Display store menu
				while (true) {
					storeMenu();
					boolean exitStoreMenu = false;
					int storeOption = sc.nextInt();
					switch(storeOption) {
					case 0:
						exitStoreMenu = true;
						break;
					case 1:
						System.out.println("Enter title of media: ");
						title = scString.nextLine();
						mediaInStore = store.searchInStore(title);
						if (mediaInStore != null) {
							System.out.println(mediaInStore.toString());
							while (true) {
								mediaDetailsMenu();
								boolean exitMediaDetailsMenu = false;
								int mediaOption = sc.nextInt();
								switch (mediaOption) {
								case 0:
									exitMediaDetailsMenu = true;
									break;
								case 1:
									cart.addMedia(mediaInStore);
									System.out.println("Media added successfully!");
									break;
								case 2:
									playMedia(mediaInStore);
									break;
								default:
									System.out.println("Invalid options, please try again");
								}
								if (exitMediaDetailsMenu) {
									break;
								}
							}
						}
						else {
							System.out.println("Media not found in store :(");
						}
						break;
					// Add media to cart
					case 2:
						System.out.println("Enter title of media: ");
						title = scString.nextLine();
						mediaInStore = store.searchInStore(title);
						if (mediaInStore != null) {
							cart.addMedia(mediaInStore);
							System.out.println("Media added sucessfully");
							System.out.println("There are " + cart.numberDVDsInCart() + " DVDs in the cart!");
						}
						else {
							System.out.println("Media not found in store :(");
						}
						break;
					// Play a media
					case 3:
						System.out.println("Enter title of media: ");
						title = scString.nextLine();
						mediaInStore = store.searchInStore(title);
						if (mediaInStore != null) {
							playMedia(mediaInStore);
						}
						else {
							System.out.println("Media not found in store :(");
						}
						break;
					case 4:
						cart.displayCart();
						break;
					default:
						System.out.println("Invalid options, please try again");
					}
					if (exitStoreMenu) {
						break;
					}
				}
				break;

			case 2:
				while (true) {
					boolean exitUpdateStoreMenu = false;
					updateStoreMenu();
					int updateOption = sc.nextInt();
					switch (updateOption) {
					case 1:
						System.out.println("Please insert the details of the DVD: ");
						System.out.println("--------------------------------");
						System.out.println("Title: ");
						String newTitle = scString.nextLine();
						System.out.println("Category: ");
						String newCategory = scString.nextLine();
						System.out.println("Cost: ");
						float newCost = sc.nextFloat();
						int newId = ++index;
						DigitalVideoDisc newDvd = new DigitalVideoDisc(newId, newTitle, newCategory, newCost);
						store.addMedia(newDvd);
						System.out.println("The media has been added to the store");
						break;
					case 2:
						System.out.println("Please insert the title of the DVD: ");
						title = scString.nextLine();
						Media DVD = store.getMedia(title);
						store.removeMedia(DVD);
						System.out.println("The media has been removed the store");
						break;
					case 0:
						exitUpdateStoreMenu = true;
						break;
					default:
						System.out.println("Invalid options, please try again");
					}
					if (exitUpdateStoreMenu) {
						break;
					}
				}
				break;
			case 3:
				cart.displayCart();
				while (true) {
					cartMenu();
					boolean exitCartMenu = false;
					int cartOption = sc.nextInt();

					switch (cartOption) {
					case 0:
						exitCartMenu = true;
						break;
					case 1:
						//filter medias by either id or title
						Media filterMedia = null;
						while (true) {
							filterMenu();
							boolean exitFilterMenu = false;
							int filterOption = sc.nextInt();
							switch (filterOption) {
							case 1:
								System.out.println("Please enter item id you want to search");
								int idFilter = sc.nextInt();
								filterMedia = cart.searchInCart(idFilter);
								exitFilterMenu = true;
								break;
							case 2:
								System.out.println("Please enter title you want to search");
								title = scString.nextLine();
								filterMedia = cart.searchInCart(title);
								exitFilterMenu = true;
								break;
							default:
								System.out.println("Invalid options, please try again");
							}
							if (exitFilterMenu) {
								break;
							}
						}

						if (filterMedia != null) {
							System.out.println("Item found: " + filterMedia.toString());
						}
						else {
							System.out.println("Item not found in cart :(");
						}

						break;
					case 2:
						// Sort medias in cart
						while (true) {
							sortMenu();
							boolean exitSortMenu = false;
							int sortOption = sc.nextInt();
							switch (sortOption) {
							case 1:
								cart.sortCartByTitle();
								exitSortMenu = true;
								break;
							case 2:
								cart.sortCartByCost();
								exitSortMenu = true;
								break;
							default:
								System.out.println("Invalid options, please try again");
							}
							if (exitSortMenu) {
								cart.displayCart();
								break;
							}
						}
						break;
					case 3:
						// Remove media
						System.out.println("Enter title of media you want to remove: ");
						title = sc.nextLine();
						mediaInCart = cart.searchInCart(title);
						if (mediaInCart != null) {
							cart.removeMedia(mediaInCart);
							System.out.println("Cart after remove the item: ");
							cart.displayCart();
						}
						else {
							System.out.println("Media not found in cart :(");
						}
						break;
					case 4:
						System.out.println("Enter title of media you want to play: ");
						title = sc.nextLine();
						mediaInCart = cart.searchInCart(title);
						if (mediaInCart != null) {
							playMedia(mediaInCart);
						}
						else {
							System.out.println("Media not found in cart :(");
						}
						break;
					case 5:
						System.out.println("An order has been created!");
						cart = new Cart();
						break;
					default:
						System.out.println("Invalid options, please try again");
					}
					if (exitCartMenu) {
						break;
					}
				}
				break;
			case 0:
				System.out.println("Goodbye! Hope We will meet again :D");
				isExit = true;
				break;
			default:
				System.out.println("Invalid option! Please try again :)");
			}
			// Exit the program
			if (isExit) {
				break;
			}
		}
	}
}
/*

		anOrder.displayItemsOrdered();
*/