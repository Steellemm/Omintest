import { ITest } from './pages/tests-data'

export interface IContextType {
    isShowPopup: boolean
    setIsShowPopup: (param: boolean) => void
    tests: ITest[]
    setTests: (param: ITest[]) => void
}
