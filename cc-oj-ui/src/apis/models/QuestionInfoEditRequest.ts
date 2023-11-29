/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { JudgeCase } from './JudgeCase';
import type { JudgeConfig } from './JudgeConfig';

export type QuestionInfoEditRequest = {
    answer?: string;
    content?: string;
    id?: number;
    judgeCaseList?: Array<JudgeCase>;
    judgeConfigDto?: JudgeConfig;
    tags?: Array<string>;
    title?: string;
};
