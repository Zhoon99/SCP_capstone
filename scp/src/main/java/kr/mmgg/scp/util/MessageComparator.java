package kr.mmgg.scp.util;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import kr.mmgg.scp.dto.MessageDto;
import lombok.SneakyThrows;

public class MessageComparator implements Comparator<MessageDto> {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");

    @SneakyThrows
    @Override
    public int compare(MessageDto a, MessageDto b) {
        Date date1 = formatter.parse(a.getMessageTime());
        Date date2 = formatter.parse(b.getMessageTime());

        if (date1.after(date2)) return 1;
        if (date1.before(date2)) return -1;

        return 0;
    }
}
