import { makeAutoObservable } from 'mobx'
import { createContext } from "react";

enum Row {
    first
}

type TableItemType = Square | null
type TableItemsListType = TableItemType[]

class SquaresStore {
    draggedPosition: PositionType | null = null
    positions: Record<Row, TableItemsListType>

    constructor() {
        makeAutoObservable(this)
    }
}