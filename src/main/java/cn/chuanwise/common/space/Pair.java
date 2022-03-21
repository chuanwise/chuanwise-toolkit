package cn.chuanwise.common.space;

import lombok.Data;

/**
 * 表示一对值
 *
 * @author Chuanwise
 */
@Data
public final class Pair<L, R> {
    
    private L key;
    
    private R value;
}
