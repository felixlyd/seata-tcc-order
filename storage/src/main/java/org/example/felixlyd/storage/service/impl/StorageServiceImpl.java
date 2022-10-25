package org.example.felixlyd.storage.service.impl;

import org.example.felixlyd.storage.service.StorageService;
import org.example.felixlyd.storage.tcc.DecreaseStorageTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/25
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private DecreaseStorageTccAction decreaseStorageTccAction;

    @Override
    public void decrease(Long productId, int count) {
        decreaseStorageTccAction.prepareDecrease(null, productId, count);
    }
}
