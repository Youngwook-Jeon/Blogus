package com.young.blogus.shared.order;

import javax.validation.GroupSequence;

// 사용하는 예를 만들기 전까지 유지하자
@GroupSequence({ FirstOrder.class, SecondOrder.class })
public interface OrderedChecks {
}
