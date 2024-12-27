package com.foodApplication.pack;

import java.util.HashMap;
import java.util.Map;

import com.dao.foodApplication.model.CartItem;

public class Cart {
    private Map<Integer, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    // Add Item
    public void addItem(CartItem item) {
    	
    	int itemId = item.getItemId();
    	if(items.containsKey(itemId))
    	{
    		CartItem existingItem = items.get(itemId);
    		existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
    	}
    	else
    	{
    		items.put(itemId, item);
    	}
    	
    	
     }

    // Update Item
    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
//            CartItem item = cart.get(itemId);
//            item.setQuantity(quantity);
            if (quantity <= 0) {
                items.remove(itemId); // Remove if quantity is zero or less
            }
            else
            {
            	CartItem existingItem = items.get(itemId);
            	existingItem.setQuantity(quantity);
            	items.get(itemId).setQuantity(quantity);
            }
            
        } 
        else 
        {
            System.out.println("Item with ID " + itemId + " not found in the cart.");
        }
    }

    // Remove Item
    public void removeItem(int itemId) {
        if (items.containsKey(itemId)) {
            items.remove(itemId);
        } else {
            System.out.println("Item with ID " + itemId + " not found in the cart.");
        }
    }

    // Get Item
    public CartItem getItem(int itemId) {
        return items.get(itemId); // Returns null if item doesn't exist
    }
    
    
    
 // Add this method to your Cart class
    public Map<Integer, CartItem> getItems() {
        return items; // returns the entire map of cart items
    }


    // Clear Cart
    public void clearCart() {
        items.clear();
        System.out.println("Cart has been cleared.");
    }

    // View Cart
    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Cart Contents:");
            for (CartItem item : items.values()) {
                System.out.println(item);
            }
        }
    }
}
