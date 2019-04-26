package util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

public class StateListTest {

//	@ParameterizedTest
//	@MethodSource("emptyStateListProvider")
//	void testStateList_EmptyStateList(StateList stateList) {
//		assertAll(
//				() -> assertFalse(stateList.hasPrevious()),
//				() -> assertFalse(stateList.hasCurrent()),
//				() -> assertFalse(stateList.hasNext()),
//				() -> assertNull(stateList.previous()),
//				() -> assertNull(stateList.current()),
//				() -> assertNull(stateList.next()),
//				() -> assertEquals(-1, stateList.previousIndex()),
//				() -> assertEquals(-1, stateList.currentIndex()),
//				() -> assertEquals(-1, stateList.nextIndex()),
//				() -> assertDoesNotThrow(stateList::remove)
//		);
//	}
//
//	@Test
//	void testStateList_CircularStateList() {
//		StateList stateList = new StateList<>(asList(0, 1, 2), true);
//		assertAll(
//				() -> assertTrue(stateList.hasCurrent()),
//				() -> assertTrue(stateList.hasPrevious()),
//				() -> assertEquals(2, stateList.previous()),
//				() -> assertTrue(stateList.hasNext()),
//				() -> assertEquals(1, stateList.previous()),
//				() -> assertEquals(0, stateList.previousIndex()),
//				() -> assertEquals(1, stateList.currentIndex()),
//				() -> assertEquals(2, stateList.nextIndex())
//		);
//	}
//
//	@Test
//	void testStateList_NonCircularStateList() {
//		StateList stateList = new StateList<>(asList(0, 1, 2));
//		assertAll(
//				() -> assertTrue(stateList.hasCurrent()),
//				() -> assertFalse(stateList.hasPrevious()),
//				() -> assertEquals(0, stateList.previous()),
//				() -> assertEquals(1, stateList.next()),
//				() -> assertEquals(2, stateList.next()),
//				() -> assertEquals(2, stateList.nextIndex()),
//				() -> assertEquals(2, stateList.next())
//		);
//	}
//
//	private static Stream<StateList> emptyStateListProvider() {
//		return Stream.of(new StateList<>(emptyList()), new StateList<>(emptyList(), true));
//	}

}
