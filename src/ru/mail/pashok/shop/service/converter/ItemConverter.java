package ru.mail.pashok.shop.service.converter;

import ru.mail.pashok.shop.repository.model.Item;
import ru.mail.pashok.shop.service.model.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemConverter {
    public static ItemDTO itemToItemDTO(Item item){
        return new ItemDTO(item.getId(), item.getItemName(), item.getDiameter(), item.getPrice());
    }
    public static Item itemDTOToItem(ItemDTO itemDTO){
        return new Item(itemDTO.getId(), itemDTO.getItemName(), itemDTO.getDiameter(), itemDTO.getPrice());
    }
    public static List<ItemDTO> itemListToItemDTOList(List<Item> items){
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for (Item item : items) {
            itemsDTO.add(itemToItemDTO(item));
        }
        return itemsDTO;
    }
}
