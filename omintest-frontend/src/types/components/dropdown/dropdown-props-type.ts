export interface IDropdownPropsType {
    error?: boolean
    items: IItemDropdown[] | undefined
}

export interface IItemDropdown {
    id: string,
    title: string
}
