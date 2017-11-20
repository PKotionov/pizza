package ru.mail.pashok.shop.repository;

import ru.mail.pashok.shop.repository.model.Item;

import java.util.List;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public interface ItemRepository {
   Item getByItemId(Long id);

    List<Item> showItems();

}
