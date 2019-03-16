package triangle

import "testing"

func TestGetTriangleType(t *testing.T) {
	tests := []struct {
		a, b, c  int
		expected triangleType
	}{
		{1001, 5, 6, UnknownTriangle},
		{5, 2001, 6, UnknownTriangle},
		{6, 5, 3001, UnknownTriangle},
		{-3, 5, 6, UnknownTriangle},
		{3, -5, 6, UnknownTriangle},
		{3, 5, -6, UnknownTriangle},
		{3,4,5,RightTriangle},
		{2,3,4,AcuteTriangle},
		{4,5,6,ObtuseTriangle},
		{1,2,3,InvalidTriangle},
		// TODO add more tests for 100% test coverage
	}

	for _, test := range tests {
		actual := getTriangleType(test.a, test.b, test.c)
		if actual != test.expected {
			t.Errorf("getTriangleType(%d, %d, %d)=%v; want %v", test.a, test.b, test.c, actual, test.expected)
		}
	}
}
