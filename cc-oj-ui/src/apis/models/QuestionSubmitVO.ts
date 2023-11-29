/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { QuestionSubmitJudgeInfo } from './QuestionSubmitJudgeInfo';
import type { UserVO } from './UserVO';

export type QuestionSubmitVO = {
    code?: string;
    createTime?: string;
    id?: number;
    language?: string;
    questionId?: number;
    status?: number;
    submitJudgeInfo?: QuestionSubmitJudgeInfo;
    user?: UserVO;
};
