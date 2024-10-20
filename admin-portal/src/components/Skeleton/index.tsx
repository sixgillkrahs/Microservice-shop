import React from "react";
import { Skeleton as ASkeleton } from "antd";
import type { SkeletonProps } from "antd";
import type { SkeletonInputProps } from "antd/lib/skeleton/Input";
import type { SkeletonImageProps } from "antd/lib/skeleton/Image";
import type { AvatarProps } from "antd/lib/skeleton/Avatar";
import type { SkeletonButtonProps } from "antd/lib/skeleton/Button";

const Skeleton: React.FC<SkeletonProps> = (props) => {
  return <ASkeleton {...props} />;
};

const SkeletonInput: React.FC<SkeletonInputProps> = (props) => {
  return <ASkeleton.Input {...props} />;
};

const SkeletonImage: React.FC<SkeletonImageProps> = (props) => {
  return <ASkeleton.Image {...props} />;
};

const SkeletonAvatar: React.FC<AvatarProps> = (props) => {
  return <ASkeleton.Avatar {...props} />;
};

const SkeletonButton: React.FC<SkeletonButtonProps> = (props) => {
  return <ASkeleton.Button {...props} />;
};

export {
  Skeleton,
  SkeletonInput,
  SkeletonImage,
  SkeletonAvatar,
  SkeletonButton,
};
