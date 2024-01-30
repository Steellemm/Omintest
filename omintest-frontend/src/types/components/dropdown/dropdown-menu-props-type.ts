import { IItemDropdown } from './dropdown-props-type.ts'

export interface IDropdownMenuPropsType {
    setInputValue: (params: string) => void
    setActive: (params: boolean) => void
    items: IItemDropdown[] | undefined
}
