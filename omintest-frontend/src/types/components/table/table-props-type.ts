export interface ITablePropsType {
    items: ITableItem []
}

export interface ITableItem {
    id: string,
    field1: string,
    field2: string
}

export interface ITableItemPropsType extends ITableItem {
    handlerRemove: (param: string) => void
}
