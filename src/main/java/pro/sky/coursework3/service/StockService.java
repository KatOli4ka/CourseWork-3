package pro.sky.coursework3.service;

import org.springframework.stereotype.Service;
import pro.sky.coursework3.exception.WrongParamException;
import pro.sky.coursework3.model.Color;
import pro.sky.coursework3.model.Size;
import pro.sky.coursework3.model.Sock;
import pro.sky.coursework3.model.SockGoods;

import java.util.*;

@Service
public class StockService {
    private final Map<Sock, Integer> socks = new HashMap<>();

    public void influx(SockGoods sockGoods) {
        if (isNotValid(sockGoods)) {
            throw new WrongParamException();
        }
        Sock sock = sockGoods.getSock();
        if (socks.containsKey(sock)) {
            socks.replace(sock, socks.get(sock) + sockGoods.getQuantity());
        } else {
            socks.put(sock, sockGoods.getQuantity());
        }
    }

    private boolean isNotValid(SockGoods sockGoods) {
        Sock sock = sockGoods.getSock();
        return sock.getCottonPart() < 0 || sock.getCottonPart() > 100 ||
                sockGoods.getQuantity() <= 0;
    }

    public void consumption(SockGoods sockGoods) {
        Sock sock = sockGoods.getSock();
        if (!socks.containsKey(sock) || isNotValid(sockGoods)) {
            throw new WrongParamException();
        }
        int available = socks.get(sock);
        int result = available - sockGoods.getQuantity();
        if (result < 0) {
            throw new WrongParamException();
        }
        socks.replace(sock, result);

    }

    public int amount(String color, float size, int cottonMin, int cottonMax) {
        Size onesize = Size.parce(size);
        Color onecolor = Color.parce(color);
        if (Objects.isNull(onecolor) || Objects.isNull(onesize) || cottonMin >= cottonMax
                || cottonMin < 0 || cottonMax > 100) {
            throw new WrongParamException();
        }
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            Sock sock = entry.getKey();
            int available = entry.getValue();
            if (sock.getColor() == onecolor && sock.getSize() == onesize &&
                    sock.getCottonPart() >= cottonMin && sock.getCottonPart() <= cottonMax) {
                return available;
            }
        }
        return 0;
    }

}
