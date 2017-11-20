package ru.mail.pashok.shop.service;

import ru.mail.pashok.shop.repository.*;
import ru.mail.pashok.shop.repository.model.Item;
import ru.mail.pashok.shop.service.converter.ItemConverter;
import ru.mail.pashok.shop.service.model.ItemDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private static ItemServiceImpl instance;

    private ItemServiceImpl() {
    }

    public static ItemService getInstance() {
        if (instance == null) {
            instance = new ItemServiceImpl();
        }
        return instance;
    }

    private ItemRepository itemRepository = ItemRepositoryImpl.getInstance();

    @Override
    public ItemDTO getItem(Long id) {
        Item item = itemRepository.getByItemId(id);
        if (item != null) {
            return ItemConverter.itemToItemDTO(item);
        }
        return null;
    }

    public static List<ItemDTO> showItems() {
        return ItemConverter.itemListToItemDTOList(ItemRepositoryImpl.getInstance().showItems());

    }
}
