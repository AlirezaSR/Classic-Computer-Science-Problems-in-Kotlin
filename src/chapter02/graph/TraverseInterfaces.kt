package chapter02.graph

interface TraverseInterfaces {

    interface InitialSupplier<T> {
        fun get(): T
    }

    interface IsItGoalPredication<T> {
        fun test(t: T): Boolean
    }

    interface IsFeasible<T> {
        fun test(t: T): Boolean
    }

    interface SuccessorsFunction<T> {
        fun apply(t: T): List<T>
    }

    interface HeuristicFunction<T> {
        fun applyAsDouble(t:T):Double
    }

    interface CostFunction<T> {
        fun apply(t: T): List<T>
    }

}