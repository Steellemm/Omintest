import { IItemDropdown } from './dropdown-props-type.ts'

export interface IDropdownPopupPropsType {
    status: boolean
    error?: boolean
    setInputValue: (params: string) => void
    setActive: (params: boolean) => void
    items: IItemDropdown[] | undefined
}
