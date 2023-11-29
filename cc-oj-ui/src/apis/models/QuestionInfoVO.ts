/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { JudgeCase } from './JudgeCase';
import type { JudgeConfig } from './JudgeConfig';
import type { UserVO } from './UserVO';

export type QuestionInfoVO = {
    acceptedNum?: number;
    acceptedRate?: number;
    content?: string;
    id?: number;
    judgeCaseList?: Array<JudgeCase>;
    judgeConfigDto?: JudgeConfig;
    submitNum?: number;
    tags?: Array<string>;
    tips?: string;
    title?: string;
    user?: UserVO;
};
